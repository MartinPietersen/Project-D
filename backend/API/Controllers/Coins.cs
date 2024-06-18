using System.Security.Claims;
using API.Data;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API.Controllers;

/// <summary>
/// Controller for managing coins for authenticated users.
/// Provides endpoints for retrieving and updating the coin balance of the authenticated user.
/// </summary>
[ApiController]
[Route("api/[controller]")]
[Authorize]
public class CoinsController : ControllerBase
{
    private readonly DataContext _context;
    
    public CoinsController(DataContext context)
    {
        _context = context;
    }
    
    /// <summary>
    /// Retrieves the amount of coins the authenticated user has.
    /// </summary>
    /// <returns>An <see cref="IActionResult"/> indicating the result of the operation
    /// Returns an <see cref="StatusCodes.Status200OK"/> if the operation is successfull
    /// Returns an <see cref="StatusCodes.Status400BadRequest"/> if the user is not authenticated or no records are found</returns>
    [HttpGet]
    [ProducesResponseType<int>(StatusCodes.Status200OK)]
    [ProducesResponseType<string>(StatusCodes.Status400BadRequest)]
    public async Task<IActionResult> GetCoins()
    {
        var userId = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;
        if (userId == null)
            return BadRequest();

        var targetUser = await _context.UserCoins.FirstOrDefaultAsync(_ => _.User.Id == userId);
        if (targetUser == null)
            return BadRequest("No records found");
        
        return Ok(targetUser.Coins);
    }
    
    
    /// <summary>
    /// Updates the coins of the authenticated user by adding the specified amount.
    /// </summary>
    /// <param name="amount">The amount of coins to add to the users current balance</param>
    /// <returns>An <see cref="IActionResult"/> indicating the result of the operation
    /// Returns an <see cref="StatusCodes.Status200OK"/> if the operation is successfull.
    /// Returns an <see cref="StatusCodes.Status400BadRequest"/> if the user is not authenticated or no records are found.</returns>
    [HttpPost]
    [ProducesResponseType<int>(StatusCodes.Status200OK)]
    [ProducesResponseType<string>(StatusCodes.Status400BadRequest)]
    public async Task<IActionResult> AddCoins([FromBody] int amount)
    {
        var userId = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;
        if (userId == null)
            return BadRequest();
        
        var targetUser = await _context.UserCoins.FirstOrDefaultAsync(_ => _.User.Id == userId);
        if (targetUser == null)
            return BadRequest("No records found");

        targetUser.Coins += amount;

        await _context.SaveChangesAsync();

        return Ok(targetUser.Coins);
    }
}
using System.Security.Claims;
using API.Data;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API.Controllers;

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
    
    [HttpGet]
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
}
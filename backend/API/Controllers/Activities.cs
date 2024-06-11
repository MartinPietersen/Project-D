using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers;

[ApiController]
[Route("api/[controller]")]
[Authorize]
public class Activities : ControllerBase
{

    [HttpGet]
    public IActionResult Test()
    {
        // Extract user information from the token
        var userId = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;
        var userName = User.Identity.Name;

        if (userId == null)
        {
            return Unauthorized();
        }
        
        Console.WriteLine($"Authenticated user name: {userName}");

        return Ok(new { UserId = userId, UserName = userName });
    }
}
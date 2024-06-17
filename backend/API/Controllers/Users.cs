using System.Security.Claims;
using API.Data;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API.Controllers;

[ApiController]
[Route("api/[controller]")]
public class Users : ControllerBase
{

    public DataContext _context { get; }

    public Users(DataContext context)
    {
        _context = context;
    }

    [HttpGet("users")]
    public IActionResult GetUsers()
    {
        var users = _context.Users.ToList();
        return Ok(users);
    }

    [HttpGet("user/{id}")]
    public IActionResult GetUser(string id)
    {
        if (id == null)
        {
            return BadRequest();
        }

        var user = _context.Users.Find(id);
        if (user == null)
        {
            return NotFound();
        }

        return Ok(user);
    }
}
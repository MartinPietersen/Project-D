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

    [HttpGet("users")] // Get all users
    public IActionResult GetUsers()
    {
        var users = _context.Users.ToList();
        return Ok(users);
    }

    [HttpGet("user/{id}")] // Get a specific user
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


    [HttpGet("user/chat/{id}")]
    public IActionResult GetUserChat(string recipientId, string senderId)
    {
        if (recipientId == null || senderId == null)
        {
            return BadRequest();
        }

        var messages = _context.ChatMessages.Where(m => m.RecipientId == recipientId && m.SenderId == senderId).ToList();
        return Ok(messages);
    }
    public async Task<List<(string Username, string LastMessage, DateTime DateSent)>> GetUsersWithLastMessagesOptimizedAsync(ApplicationDbContext context)
    {
        var usersWithLastMessages = await context.ChatMessages
            .GroupBy(cm => new { cm.SenderId, cm.Sender.UserName })
            .Select(g => new
            {
                g.Key.UserName,
                LastMessage = g.OrderByDescending(cm => cm.DateSent).FirstOrDefault()
            })
            .Union(
                context.ChatMessages
                .GroupBy(cm => new { cm.RecipientId, cm.Recipient.UserName })
                .Select(g => new
                {
                    g.Key.UserName,
                    LastMessage = g.OrderByDescending(cm => cm.DateSent).FirstOrDefault()
                })
            )
            .GroupBy(x => x.UserName)
            .Select(g => new
            {
                g.Key,
                LastMessage = g.OrderByDescending(x => x.LastMessage.DateSent).FirstOrDefault().LastMessage
            })
            .Select(x => new
            {
                Username = x.Key,
                LastMessage = x.LastMessage.Content,
                DateSent = x.LastMessage.DateSent
            })
            .ToListAsync();

        return usersWithLastMessages
            .Select(x => (x.Username, x.LastMessage, x.DateSent))
            .ToList();
    }
}
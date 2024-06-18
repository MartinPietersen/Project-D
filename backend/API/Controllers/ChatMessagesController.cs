using API.Data;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

[ApiController]
[Route("api/[controller]")]

public class ChatMessagesController : ControllerBase
{
    private readonly DataContext _context;

    public ChatMessagesController(DataContext context)
    {
        _context = context;
    }

    [HttpGet("messages")]
    public async Task<IActionResult> GetMessages()
    {
        var messages = await _context.ChatMessages.ToListAsync();
        return Ok(messages);
    }

    [HttpGet("message/{id}")] // Get a specific message
    public async Task<IActionResult> GetMessage(string id)
    {
        if (id == null)
        {
            return BadRequest();
        }

        var message = await _context.ChatMessages.FindAsync(id);
        if (message == null)
        {
            return NotFound();
        }

        return Ok(message);
    }

    [HttpGet("message/{senderId}/{recipientId}")] // Get messages between two users
    public async Task<IActionResult> GetMessages(string senderId, string recipientId)
    {
        if (senderId == null || recipientId == null)
        {
            return BadRequest();
        }

        var messages = await _context.ChatMessages.Where(m => m.SenderId == senderId && m.RecipientId == recipientId).ToListAsync();
        return Ok(messages);
    }


    [HttpPost("message")]
    [ProducesResponseType(StatusCodes.Status201Created)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    public async Task<IActionResult> SendMessage([FromBody] ChatMessage message)
    {
        if (message == null)
        {
            return BadRequest();
        }

        await _context.ChatMessages.AddAsync(message);
        _context.SaveChanges();

        return Ok(message);
    }

    [HttpDelete("message/{id}")]
    public async Task<IActionResult> DeleteMessage(string id)
    {
        if (id == null)
        {
            return BadRequest();
        }

        var message = await _context.ChatMessages.FindAsync(id);
        if (message == null)
        {
            return NotFound();
        }

        _context.ChatMessages.Remove(message);
        _context.SaveChanges();

        return Ok();
    }
}
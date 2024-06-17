


using API.Data;
using Microsoft.AspNetCore.Mvc;

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
    public async IActionResult GetMessages() // Get all messages
    {
        var messages = await _context.ChatMessages.ToListAsync();
        return Ok(messages);
    }

    [HttpGet("message/{id}")] // Get a specific message
    public IActionResult GetMessage(string id)
    {
        if (id == null)
        {
            return BadRequest();
        }

        var message = _context.ChatMessages.Find(id);
        if (message == null)
        {
            return NotFound();
        }

        return Ok(message);
    }

    [HttpGet("message/{senderId}/{recipientId}")] // Get messages between two users
    public IActionResult GetMessages(string senderId, string recipientId)
    {
        if (senderId == null || recipientId == null)
        {
            return BadRequest();
        }

        var messages = _context.ChatMessages.Where(m => m.SenderId == senderId && m.RecipientId == recipientId).ToList();
        return Ok(messages);
    }


    [HttpPost("message")]
    public IActionResult SendMessage([FromBody] ChatMessage message)
    {
        if (message == null)
        {
            return BadRequest();
        }

        _context.ChatMessages.Add(message);
        _context.SaveChanges();

        return Ok(message);
    }

    [HttpDelete("message/{id}")]
    public IActionResult DeleteMessage(string id)
    {
        if (id == null)
        {
            return BadRequest();
        }

        var message = _context.ChatMessages.Find(id);
        if (message == null)
        {
            return NotFound();
        }

        _context.ChatMessages.Remove(message);
        _context.SaveChanges();

        return Ok();
    }
}
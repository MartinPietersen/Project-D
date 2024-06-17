


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
    public IActionResult GetMessages()
    {
        var messages = _context.ChatMessages.ToList();
        return Ok(messages);
    }

    [HttpGet("message/{id}")]
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

    [HttpGet("message/{senderId}/{recipientId}")]
    public IActionResult GetMessages(string senderId, string recipientId)
    {
        if (senderId == null || recipientId == null)
        {
            return BadRequest();
        }

        var messages = _context.ChatMessages.Where(m => m.SenderId == senderId && m.RecipientId == recipientId).ToList();
        return Ok(messages);
    }
}
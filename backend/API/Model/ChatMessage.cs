


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using API.Model;

public class ChatMessage
{
    [Key]
    public Guid Id { get; set; }
    [ForeignKey("Sender")]
    public string SenderId { get; set; }
    [ForeignKey("Recipient")]
    public string RecipientId { get; set; }
    public string Content { get; set; }
    public DateTime DateSent { get; set; }

    public virtual User Sender { get; set; }
    public virtual User Recipient { get; set; }


#pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider adding the 'required' modifier or declaring as nullable.
    public ChatMessage()
    {

    }

    public ChatMessage(string senderId, string recipientId, string content, DateTime dateSent)
    {
        SenderId = senderId;
        RecipientId = recipientId;
        Content = content;
        DateSent = dateSent;
    }
}

#pragma warning restore CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider adding the 'required' modifier or declaring as nullable.
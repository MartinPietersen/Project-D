using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;

namespace API.Model;

[Table("Users")]
public class User : IdentityUser
{

    // ChatMessages
    public virtual ICollection<ChatMessage> SentMessages { get; set; }
    public virtual ICollection<ChatMessage> ReceivedMessages { get; set; }

#pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider adding the 'required' modifier or declaring as nullable.
    public User()
    {
    }
#pragma warning restore CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider adding the 'required' modifier or declaring as nullable.
}
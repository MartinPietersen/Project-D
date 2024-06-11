using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;

namespace API.Model;

[Table("Users")]
public class User : IdentityUser
{
    
}
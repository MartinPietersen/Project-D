using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace API.Model;

public class RegisterViewModel
{
	[Required]
	[EmailAddress]
	public string Email { get; set; }

	[Required]
	[DataType(DataType.Password)]
	public string Password { get; set;}
}
	

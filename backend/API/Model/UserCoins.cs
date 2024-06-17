using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace API.Model;

[Table("UserCoins")]
public class UserCoins
{
	[Key]
	public int Id { get; set; }

	public virtual User User { get; set; }

	public int Coins { get; set; } = 0;

}

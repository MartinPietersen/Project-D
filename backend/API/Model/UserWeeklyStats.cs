using System.ComponentModel.DataAnnotations;

namespace API.Model;

public class UserWeeklyStats
{
    [Key]
    public int Id { get; set; }
    
    public User User { get; set; }
    
    public int Steps { get; set; }
    
    public float HoursActive { get; set; }
    
    public int CoinsCollected { get; set; }
}
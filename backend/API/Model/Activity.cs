using System.ComponentModel.DataAnnotations;

namespace API.Model;

public class Activity
{
    [Key]
    public Guid Id { get; set; }
    
    public string Title { get; set; }
}
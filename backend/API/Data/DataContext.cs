using API.Model;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace API.Data;

public class DataContext(DbContextOptions<DataContext> options) : IdentityDbContext<User>(options)
{
    public DbSet<User> Users { get; set; }
<<<<<<< Updated upstream
    public DbSet<ChatMessage> ChatMessages { get; set; }
=======
	 public DbSet<UserCoins> UserCoins { get; set; }
>>>>>>> Stashed changes
    //
    
    protected override void OnConfiguring(DbContextOptionsBuilder options) // Configure the connection to the database
    {
        var serverVersion = new MySqlServerVersion(new Version(8, 0, 23));
        options.UseMySql("server=104.248.197.46;user=root;database=Bama;port=3306;password=example", serverVersion,
            mysqlOptions => mysqlOptions.EnableRetryOnFailure());
    }
<<<<<<< Updated upstream

    protected override void OnModelCreating(ModelBuilder modelBuilder) // Define the relationships between the tables
    {
        base.OnModelCreating(modelBuilder);
        
        modelBuilder.Entity<User>().HasMany(u => u.SentMessages).WithOne(c => c.Sender).HasForeignKey(c => c.SenderId);
        modelBuilder.Entity<User>().HasMany(u => u.ReceivedMessages).WithOne(c => c.Recipient).HasForeignKey(c => c.RecipientId);

        modelBuilder.Entity<ChatMessage>()
                .HasOne(cm => cm.Sender)
                .WithMany(u => u.SentMessages)
                .HasForeignKey(cm => cm.SenderId)
                .OnDelete(DeleteBehavior.Restrict);

        modelBuilder.Entity<ChatMessage>()
                .HasOne(cm => cm.Recipient)
                .WithMany(u => u.ReceivedMessages)
                .HasForeignKey(cm => cm.RecipientId)
                .OnDelete(DeleteBehavior.Restrict);
    }
}
=======
}
>>>>>>> Stashed changes

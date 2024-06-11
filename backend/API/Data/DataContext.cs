using API.Model;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace API.Data;

public class DataContext(DbContextOptions<DataContext> options) : IdentityDbContext<User>(options)
{
    public DbSet<User> Users { get; set; }
    //
    
    protected override void OnConfiguring(DbContextOptionsBuilder options)
    {
        var serverVersion = new MySqlServerVersion(new Version(8, 0, 23));
        options.UseMySql("server=104.248.197.46;user=root;database=Bama;port=3306;password=example", serverVersion,
            mysqlOptions => mysqlOptions.EnableRetryOnFailure());
    }
}
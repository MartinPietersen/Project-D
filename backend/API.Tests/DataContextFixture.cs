using API.Data;
using Microsoft.EntityFrameworkCore;

namespace API.Tests;

/// <summary>
/// Provides the DataContext needed for in the controllers and disposed the DataContext after each test class is executed.
/// https://xunit.net/docs/shared-context
/// </summary>
public class DataContextFixture : IDisposable
{
    public DataContext Context { get; private set; }

    public DataContextFixture()
    {
        var options = new DbContextOptionsBuilder<DataContext>()
            .UseInMemoryDatabase(databaseName: $"TestDatabase_{Guid.NewGuid()}")
            .Options;

        Context = new DataContext(options);
    }

    public void Dispose()
    {
        Context.Database.EnsureDeleted();
        Context.Dispose();
    }

}
using Microsoft.AspNetCore.Mvc.Testing;

namespace API.Tests.IntergrationTests;

public class IntegrationTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly WebApplicationFactory<Program> _factory;

    public IntegrationTests(WebApplicationFactory<Program> factory)
    {
        _factory = factory;
    }
    
    [Theory]
    [InlineData("/api/Activities")]
    [InlineData("/api/Coins")]
    public async Task GetAuthorizedEndpoint_UnauthorizedWithoutToken(string url)
    {
        var client = _factory.CreateClient();


        // Act
        var response = await client.GetAsync(url);

        // Assert
        Assert.Equal(System.Net.HttpStatusCode.Unauthorized, response.StatusCode);
    }
}
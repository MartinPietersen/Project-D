using System.Security.Claims;
using API.Controllers;
using API.Data;
using API.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Moq;
using Xunit.Abstractions;

namespace API.Tests;

public class CoinsControllerTests : IClassFixture<DataContextFixture>
{
    private CoinsController _controller;
    private readonly DataContext _context;
    
    public CoinsControllerTests(DataContextFixture fixture)
    {
        _context = fixture.Context;
        
        SeedDatabase();
        InitializeController();
    }

    private void SeedDatabase()
    {
        _context.UserCoins.RemoveRange(_context.UserCoins);
        _context.Users.RemoveRange(_context.Users);
        _context.SaveChanges();
        
        var user = new User { Id = "test-user-id", UserName = "testuser" };
        _context.Users.Add(user);
        _context.UserCoins.Add(new UserCoins { User = user, Coins = 100 });
        _context.SaveChanges();
    }
    
    private void InitializeController()
    {
        _controller = new CoinsController(_context);
        
        var userMock = new ClaimsPrincipal(new ClaimsIdentity(new Claim[]
        {
            new Claim(ClaimTypes.NameIdentifier, "test-user-id")
        }, "mock"));

        _controller.ControllerContext = new ControllerContext
        {
            HttpContext = new DefaultHttpContext { User = userMock }
        };
    }

    [Fact]
    public async Task GetCoins_ReturnsUserCoins()
    {
        var result = await _controller.GetCoins() as OkObjectResult;
        
        Assert.NotNull(result);
        Assert.Equal(200, result.StatusCode);
        Assert.Equal(100, result.Value);
    }

    [Fact]
    public async Task GetCoins_UserNotFound_ReturnsBadRequest()
    {
        var userMock = new ClaimsPrincipal(new ClaimsIdentity(new Claim[]
        {
            new Claim(ClaimTypes.NameIdentifier, "invalid-user-id")
        }, "mock"));

        _controller.ControllerContext = new ControllerContext
        {
            HttpContext = new DefaultHttpContext { User = userMock }
        };

        var result = await _controller.GetCoins() as BadRequestObjectResult;
        
        Assert.NotNull(result);
        Assert.Equal(400, result.StatusCode);
        Assert.Equal("No records found", result.Value);
    }

    [Fact]
    public async Task AddCoins_ReturnsOk()
    {
        const int COINS_TO_ADD = 30;
        var result = await _controller.AddCoins(COINS_TO_ADD) as OkObjectResult;
        
        // Initial coins are 100
        Assert.NotNull(result);
        Assert.Equal(200, result.StatusCode);
        Assert.Equal(130, result.Value);
    }
    
}
using API.Data;
using API.Model;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers;

[ApiController]
public class AccountController : ControllerBase 
{
	private readonly UserManager<User> _userManager;
	private readonly SignInManager<User> _signInManager;
	private readonly ILogger<AccountController> _logger;
	private readonly DataContext _dataContext;

	public AccountController(
		UserManager<User> userManager,
		SignInManager<User> signInManager,
		ILogger<AccountController> logger,
		DataContext context
	)
	{
		_userManager = userManager;
		_signInManager = signInManager;
		_logger = logger;
		_dataContext = context;
	}

	[HttpPost]
	[Route("register")]
	public async Task<IActionResult> Register(RegisterViewModel model)
	{
		if(!ModelState.IsValid)
			return BadRequest(new { Errors = ModelState.Values.SelectMany(v => v.Errors) });

		var user = new User { UserName = model.Email, Email = model.Email };
		var result = await _userManager.CreateAsync(user, model.Password);
		if(result.Succeeded)
		{
			_logger.LogInformation("User created with a new password");


			var userCoinProfile = new UserCoins
			{
				User = user,
				Coins = 0
			};

			_dataContext.UserCoins.Add(userCoinProfile);

			await _dataContext.SaveChangesAsync();

			return Ok("Registration Succeeded");
		}
		foreach(var error in result.Errors)
		{
			ModelState.AddModelError(string.Empty, error.Description);
		}

		return BadRequest(new { Errors = ModelState.Values.SelectMany(v => v.Errors) });
	}
}
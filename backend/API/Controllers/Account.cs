using API.Data;
using API.Model;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers;


/// <summary>
/// [NOT FINISHED]Controller for user account management.
/// Provides endpoints for user registration and other account related operations/logic.
/// </summary>
/// <remarks>
/// <para>TODO:</para>
/// <list type="bullet">
/// <item><description>Add endpoint to allow custom user login. (remove of MapIdentityApi)</description></item>
/// <item><description>Make generic model for authenticating users</description></item>
/// </list>
/// </remarks>
[ApiController]
public class AccountController : ControllerBase 
{
	private readonly UserManager<User> _userManager;
	private readonly SignInManager<User> _signInManager;
	private readonly ILogger<AccountController> _logger;
	private readonly DataContext _dataContext;

	/// <summary>
	/// Initializes a new instance of the <see cref="AccountController"/> class.
	/// </summary>
	/// <param name="userManager">The user manager for managing user accounts.</param>
	/// <param name="signInManager">The sign-in manager for managing user sign-ins.</param>
	/// <param name="logger">The logger for logging information and errors.</param>
	/// <param name="context">The data context used for database operations.</param>
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

	/// <summary>
	/// Registers a new user with the specified registration details.
	/// </summary>
	/// <param name="model">The registration details provided by the user.</param>
	/// <returns>An <see cref="IActionResult"/> indicating the result of the registration operation.
	/// Returns an <see cref="StatusCodes.Status200OK"/> if the registration is successful.
	/// Returns an <see cref="StatusCodes.Status400BadRequest"/> if the registration fails or the model state is invalid.</returns>
	[HttpPost]
	[Route("register")]
	[ProducesResponseType(StatusCodes.Status200OK)]
	[ProducesResponseType(StatusCodes.Status400BadRequest)]
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
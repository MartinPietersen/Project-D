using API.Data;
using API.Utils;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;

namespace API.Services.User;
using API.Model;

// public sealed class UserService(DataContext context) : IUserService
// {
//     private readonly DataContext _context = context;
//     
//     public async Task<bool> AddUser(UserRegistrationModel user)
//     {
//         try
//         {
//             var hash = PasswordHasher.HashPassword(user.Password, out var salt);
//             UserSecret userSecret = new UserSecret() { PasswordHash = hash, PasswordSalt = salt };
//
//
//             User newUser = new User()
//             {
//                 FirstName = user.FirstName,
//                 LastName = user.LastName,
//                 Email = user.Email,
//                 PhoneNumber = user.PhoneNumber ?? string.Empty,
//                 MiddleName = user.MiddleName ?? string.Empty,
//                 UserSecret = userSecret
//             };
//
//             await _context.Users.AddAsync(newUser);
//             await _context.SaveChangesAsync();
//
//             return true;
//
//         }
//         catch (Exception e)
//         {
//             Console.WriteLine(e);
//             throw;
//         }
//         return false;
//     }
//
//     public async Task<User?> FindUserByEmail(string email) =>
//         await _context.Users.FirstOrDefaultAsync(user => user.Email == email);
// }
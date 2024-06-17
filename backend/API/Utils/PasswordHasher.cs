using System.Security.Cryptography;
using System.Text;

namespace API.Utils;

public static class PasswordHasher
{
    private const int KeySize = 64;
    private const int Iterations = 350000;
    private static HashAlgorithmName AlgorithmName = HashAlgorithmName.SHA512;
    
    
    public static byte[] HashPassword(string password, out byte[] salt) // Hash the password
    {
        salt = RandomNumberGenerator.GetBytes(KeySize);

        var hash = Rfc2898DeriveBytes.Pbkdf2(Encoding.UTF8.GetBytes(password),
            salt,Iterations, AlgorithmName , KeySize);

        return hash;
    }

    public static bool VerifiyPassword(string password, byte[] hash, byte[] salt) // Verify the password
    {
        var compare = Rfc2898DeriveBytes.Pbkdf2(password, salt, Iterations, AlgorithmName, KeySize);

        return CryptographicOperations.FixedTimeEquals(compare, hash);
    }
}
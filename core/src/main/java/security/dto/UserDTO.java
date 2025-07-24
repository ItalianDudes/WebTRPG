package security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import security.enums.UserRole;

@Getter
@AllArgsConstructor
@ToString(exclude = "plainPassword")
@EqualsAndHashCode(exclude = "plainPassword")
public class UserDTO {

    // Attributes
    @NotNull @NotBlank(message = "Username obbligatorio") private final String username;
    @NotNull @Email(message = "Email non valida") @NotBlank(message = "La mail è obbligatoria") private final String mail;
    @NotNull @NotBlank(message = "Password obbligatoria") @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "La password deve contenere almeno una maiuscola, una minuscola, un numero e un simbolo"
    ) private final String plainPassword;
    @NotNull private final UserRole role;
}

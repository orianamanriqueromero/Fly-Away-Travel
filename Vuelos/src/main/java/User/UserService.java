package User;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    //crear mtodo para registro del usuario

    public User registro(User user){
        //email valido:

        if (!user.getEmail().contains("@") ){
            throw new RuntimeException("Mail inválido, vuelva a intentar");
        }
        if (!user.getEmail().contains(".com") ){
            throw new RuntimeException("Mail inválido, vuelva a intentar");
        }

        //nombre y apellido minimo 1 letra mayuscula
        if (!Character.isUpperCase(user.getFirstName().charAt(0))){
           throw new RuntimeException("El nombre debe comenzar por mayúscula");
        }

        if (!Character.isUpperCase(user.getLastName().charAt(0))){
            throw new RuntimeException("El apellido debe comenzar por mayúscula");
        }
        //constraseña minimo 8 caracteres, al menos 1 letra y 1 número

        if (user.getPassword().length()<8){
            throw new RuntimeException("La constraseña debe tener 8 caracteres o más");
        }

        if (!user.getPassword().matches(".*[a-zA-Z].*") || !user.getPassword().matches(".*\\d.*")) {
            throw new RuntimeException("La contraseña debe tener al menos una letra y un número");
        }

        return userRepository.save(user);   //alias para guardar cosas: no se puede usar nombre de la interfaz
    }



}

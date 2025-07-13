package utilerias;

public interface Messages {

    String ACCESS_CORRECT = "Bienvenido.";

    String USER_NOT_FOUND = "El usuario no fue encontrado";

    String USER_EXIST = "El usuario ya esta registrado";

    String USER_INACTIVE = "El usuario se encuentra inactivo";

    String PASSWORD_INCORRECT = "La contraseña actual es incorrecta.";

    String PASSWORD_REPEAT = "La nueva contraseña es igual a la anterior, vuelva a intentar.";

    String PASSWORD_RESET = "Se reestablecio la contraseña del usuario y se notificó al correo registrado.";

    String DUPLICATE_USER = "Ya existe un usuario registrado con los datos de la persona.";

    String DUPLICATE_NAME_USER = "Ya existe un usuario con la cuenta de correo ingresada.";

    String UPDATED = "Registro actualizado";

    String SENT = "Papeleta enviada para autorización";

    String CREATED = "Registro agregado.";

    String ERROR_PROCESO = "Error en el proceso.";

    String REVERSE = "Solicitud cambiada a estado inicial";

    String DELETED = "Registro eliminado";
    String ERROR_DELETED = "Error al eliminar el usuario.";

    String BUSCAR_REGISTTRO = "Registro encontado";
    String ERROR_BUSCAR_REGISTRO = "Registro no encontado";
}

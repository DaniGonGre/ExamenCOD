import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import javax.swing.*;

/**
 *  Para importar la libreria de discord entré en el enlace de
 *  discord4j.com y copié las "dependencies" de Gradle para pegarlas en
 *  el proyecto en el apartado "build.gradle". De esta forma podemos
 *  importar la libreria en esta clase.
 */

public final class ExampleBot {

    public static void main(final String[] args) {

        /**
         * Primero 3 creamos constantes, uno llamado token de tipo
         * String y otros dos creados a partir de dos clases sacadas
         * de las librerias importadas con anterioridad, llamadas client
         * y gateway. Para obtener el token debemos crear una aplicación
         * en Discord Developer Potal. Para crearla primero nos registramos
         * y le damos a "New Aplication", aquí añadimos el nombre que queramos.
         * Con la aplicación creada le damos a las tres rayas de la izquierda y
         * en el apartado Settings pulsamos "Bot". De esta manera podremos añadir
         * un Bot dándole a "Add Bot" en el apartado Build-A-Bot. En mi caso,
         * al pulsar el botón me dice "You are being rate limited." debido a que
         * han creado demasiados bots no me permite crearlo.
         */

        final String token = JOptionPane.showInputDialog("Introduce el token: ");
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        /** El bot devolverá la palabra "Pong!" si nosotros añadimos la palabra
         *  "!ping".
         */

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }
        });

        gateway.onDisconnect().block();
    }
}




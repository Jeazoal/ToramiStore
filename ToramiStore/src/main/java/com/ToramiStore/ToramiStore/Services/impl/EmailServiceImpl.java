package com.ToramiStore.ToramiStore.Services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationUrl) {
        String subject = "Verificación de cuenta - ToramiStore";

        String message = "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                + "<h2 style='color: #333;'>¡Bienvenido a ToramiStore! 🎉</h2>"
                + "<p>Gracias por registrarte en <b>ToramiStore</b>. Para activar tu cuenta, haz clic en el siguiente botón:</p>"
                + "<a href='" + verificationUrl + "' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; text-decoration: none; border-radius: 5px;'>Verificar Cuenta</a>"
                + "<p>O también puedes copiar y pegar el siguiente enlace en tu navegador:</p>"
                + "<p><a href='" + verificationUrl + "'>" + verificationUrl + "</a></p>"
                + "<p>Si no solicitaste esta cuenta, ignora este mensaje.</p>"
                + "<hr style='margin-top: 20px;'/>"
                + "<p style='font-size: 12px; color: #777;'>Este es un mensaje automático, por favor no respondas a este correo.</p>"
                + "</div>";

        sendEmail(to, subject, message, true);
    }

    public void sendPasswordResetEmail(String to, String resetUrl) {
        String subject = "Recuperación de Contraseña - ToramiStore";

        String message = "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                + "<h2 style='color: #333;'>Recuperación de Contraseña 🔑</h2>"
                + "<p>Has solicitado restablecer tu contraseña. Haz clic en el siguiente botón para continuar:</p>"
                + "<a href='" + resetUrl + "' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #007bff; text-decoration: none; border-radius: 5px;'>Restablecer Contraseña</a>"
                + "<p>O también puedes copiar y pegar el siguiente enlace en tu navegador:</p>"
                + "<p><a href='" + resetUrl + "'>" + resetUrl + "</a></p>"
                + "<p>Si no solicitaste este cambio, ignora este correo.</p>"
                + "<hr style='margin-top: 20px;'/>"
                + "<p style='font-size: 12px; color: #777;'>Este es un mensaje automático, por favor no respondas a este correo.</p>"
                + "</div>";

        sendEmail(to, subject, message, true);
    }

    public void sendEmail(String to, String subject, String content, boolean isHtml) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo: " + e.getMessage());
        }
    }

}
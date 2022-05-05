package com.lecode.view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Component;

@Component
public class LecodeView extends JFrame {

    public LecodeView() {
        initUI();
    }

    private String getIpv4() throws UnknownHostException {
        try {
            if (SystemUtils.IS_OS_WINDOWS) {

                String ip = Inet4Address.getLocalHost().getHostAddress();

                return ip;

            } else {
                List<String> list = new ArrayList<>();

                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface iface = interfaces.nextElement();
                    if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint()) {
                        continue;
                    }

                    Enumeration<InetAddress> addresses = iface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();

                        final String ip = addr.getHostAddress();
                        if (Inet4Address.class == addr.getClass()) {
                            list.add(ip);
                        }
                    }
                }
                return list.get(list.size() - 1);
            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void initUI() {
        try {

            String ip4v = getIpv4();
            String QrCodeData = "http://" + ip4v + ":8080";
            String filePath = currentPath() + "/qrcode.png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(QrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 250, 250, hintMap);

            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            System.out.println("Gerado arquivo em " + filePath);

            ImageIcon icon = new ImageIcon(currentPath() + "/qrcode.png");
            JLabel label = new JLabel(icon);

            createLayout(label);

            setTitle("Sincronização");
            setSize(300, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void createLayout(JComponent... arg) throws Exception {
        try {
            var pane = getContentPane();
            var gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);

            gl.setHorizontalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
            );

        } catch (Exception e) {
            throw e;
        }

    }

    private String currentPath() {
        Path path = Paths.get("");
        return path.toAbsolutePath().normalize().toString();
    }

}

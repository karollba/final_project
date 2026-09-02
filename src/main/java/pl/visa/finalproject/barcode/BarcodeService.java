package pl.visa.finalproject.barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class BarcodeService {
    public String decodeBarcode(MultipartFile file) throws IOException, NotFoundException {
        BufferedImage image = ImageIO.read(file.getInputStream());

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}

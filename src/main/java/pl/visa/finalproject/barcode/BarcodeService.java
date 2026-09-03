package pl.visa.finalproject.barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BarcodeService {
    public String decodeBarcode(MultipartFile file) throws IOException, NotFoundException {
        BufferedImage image = ImageIO.read(file.getInputStream());

        LuminanceSource source = new BufferedImageLuminanceSource(image);

        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            return new MultiFormatReader().decode(binaryBitmap, hints).getText();
        } catch (NotFoundException e) {
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            return new MultiFormatReader().decode(bitmap).getText();
        }
    }

}

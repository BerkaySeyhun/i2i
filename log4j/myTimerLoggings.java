import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class myTimerLoggings {
    private static final Logger logger = LogManager.getLogger(myTimerLoggings.class);

    public static void main(String[] args) {
        // log4j yapılandırma dosyasının konumu
        String log4jConfigFile = "/Java/src/log4j2.xml";
        Configurator.initialize(null, log4jConfigFile);

        // Debug seviyesindeki logların döngüsü
        for (int i = 0; i < 10; i++) {
            logger.debug("Debug Log: " + i + " seconds passed.");
            try {
                Thread.sleep(1000); // 1 saniye bekletmek içi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Info seviyesindeki logların döngüsü
        for (int i = 0; i < 10; i++) {
            logger.info("Info Log: " + i + " minutes passed.");
            try {
                Thread.sleep(60000); // 1 dakika bekletmek için
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Error seviyesindeki logların döngüsü
        for (int i = 0; i < 10; i++) {
            logger.error("Error Log: " + i + " hours passed.");
            try {
                Thread.sleep(3600000); // 1 saat beklemtek için
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

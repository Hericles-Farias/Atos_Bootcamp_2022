import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
  public Logger logger;
  FileHandler fh;
  //verifica se o arquivo de log ja existe, senao cria
  public Log(String nome_arquivo) throws IOException {
    File f = new File(nome_arquivo);
    if (!f.exists()){
        f.createNewFile();
    }

  fh = new FileHandler(nome_arquivo, true);
  fh.setEncoding("UTF-8");
  logger = Logger.getLogger("Teste Log");
  logger.addHandler(fh);
  SimpleFormatter formatter = new SimpleFormatter();
  fh.setFormatter(formatter);

    }
}

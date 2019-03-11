package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti, Julien Benoit
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

      vistor.visit(rootDirectory);

      if (rootDirectory.isDirectory()) {
        File[] ArrayOfFiles = rootDirectory.listFiles();
        Arrays.sort(ArrayOfFiles);

        //explore récursivement tout les dossiers ou fichier afin d'appeler la méthode visit sur ce dernier.
        for (File file : ArrayOfFiles){
          explore(file,vistor);
        }
      }

  }

}

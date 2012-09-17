package ij.plugin.hdr.reponse;

/**
*
* @author Alex
*/
public class ResponseFunctionCalculatorSettings {
   private int noOfImages;
   private int noOfPixelsN;
   private int noOfChannels;
   private double[] expTimes;
   private int Zmin;
   private int Zmax;
   private int height;
   private int width;
   private String fileName;
   private int type;
   private int levels;

   public ResponseFunctionCalculatorSettings() {
   }

   /**
    * @return the noOfImagesP
    */
   public int getNoOfImages() {
       return noOfImages;
   }

   /**
    * @param noOfImagesP the noOfImagesP to set
    */
   public void setNoOfImages(int noOfImages) {
       this.noOfImages = noOfImages;
   }

   /**
    * @return the noOfPixelsN
    */
   public int getNoOfPixelsN() {
       return noOfPixelsN;
   }

   /**
    * @param noOfPixelsN the noOfPixelsN to set
    */
   public void setNoOfPixelsN(int noOfPixelsN) {
       this.noOfPixelsN = noOfPixelsN;
   }

   /**
    * @return the noOfChannels
    */
   public int getNoOfChannels() {
       return noOfChannels;
   }

   /**
    * @param noOfChannels the noOfChannels to set
    */
   public void setNoOfChannels(int noOfChannels) {
       this.noOfChannels = noOfChannels;
   }

   /**
    * @return the shutterSpeeds
    */
   public double[] getExpTimes() {
       return expTimes;
   }

   /**
    * @param shutterSpeeds the shutterSpeeds to set
    */
   public void setExpTimes(double[] expTimes) {
       this.expTimes = expTimes;
   }

   /**
    * @return the Zmin
    */
   public int getZmin() {
       return Zmin;
   }

   /**
    * @param Zmin the Zmin to set
    */
   public void setZmin(int Zmin) {
       this.Zmin = Zmin;
   }

   /**
    * @return the Zmax
    */
   public int getZmax() {
       return Zmax;
   }

   /**
    * @param Zmax the Zmax to set
    */
   public void setZmax(int Zmax) {
       this.Zmax = Zmax;
   }

   /**
    * @return the height
    */
   public int getHeight() {
       return height;
   }

   /**
    * @param height the height to set
    */
   public void setHeight(int height) {
       this.height = height;
   }

   /**
    * @return the width
    */
   public int getWidth() {
       return width;
   }

   /**
    * @param width the width to set
    */
   public void setWidth(int width) {
       this.width = width;
   }

   /**
    * @return the fileName
    */
   public String getFileName() {
       return fileName;
   }

   /**
    * @param fileName the fileName to set
    */
   public void setFileName(String fileName) {
       this.fileName = fileName;
   }

   /**
    * @return the type
    */
   public int getType() {
       return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(int type) {
       this.type = type;
   }

   /**
    * @return the levels
    */
   public int getLevels() {
       return levels;
   }

   /**
    * @param levels the levels to set
    */
   public void setLevels(int levels) {
       this.levels = levels;
   }
}

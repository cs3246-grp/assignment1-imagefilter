package ij.plugin.hdr.calibration.ZMatrix;

import ij.plugin.hdr.Exceptions.TypeNotSupportedException;


/**
 *
 * @author Alexander Heidrich
 */
public interface ZMatrixBuilder {
  int[][][] getZ() throws TypeNotSupportedException;
}
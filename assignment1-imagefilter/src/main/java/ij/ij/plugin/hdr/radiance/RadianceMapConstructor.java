package ij.plugin.hdr.radiance;

import ij.plugin.hdr.Exceptions.TypeNotSupportedException;

/**
 *
 * @author Alexander Heidrich <alexander.heidrich@hki-jena.de>
 */
public interface RadianceMapConstructor {
    public double[] calcRadiance(int channel) throws TypeNotSupportedException;
}
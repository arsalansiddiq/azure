package com.inventa.azure.common;


public class SizeConverter {


    public static Long byteToGb(Long bytes){
        if(bytes!=null && bytes > 0) {
            bytes = (((bytes / 1024) / 1024) / 1024);
            return bytes;
        }
        return bytes == null ? 0L : bytes;
    }

    public static Long mbToGb(Long bytes){
        if(bytes!=null && bytes > 0) {
            bytes = bytes / 1024;
            return bytes;
        }
        return bytes == null ? 0L : bytes;
    }

    public static Long mbToGb(Integer bytes){
        if(bytes!=null && bytes > 0) {
            bytes = bytes / 1024;
            return bytes.longValue();
        }
        return bytes == null ? 0L : bytes.longValue() ;
    }

}

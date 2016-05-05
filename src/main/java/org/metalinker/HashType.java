//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.26 at 01:34:15 PM CEST 
//


package org.metalinker;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hashType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="hashType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ed2k"/>
 *     &lt;enumeration value="md4"/>
 *     &lt;enumeration value="md5"/>
 *     &lt;enumeration value="sha1"/>
 *     &lt;enumeration value="sha256"/>
 *     &lt;enumeration value="sha384"/>
 *     &lt;enumeration value="sha512"/>
 *     &lt;enumeration value="rmd160"/>
 *     &lt;enumeration value="tiger"/>
 *     &lt;enumeration value="crc32"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "hashType")
@XmlEnum
public enum HashType {

    @XmlEnumValue("ed2k")
    ED_2_K("ed2k"),
    @XmlEnumValue("md4")
    MD_4("md4"),
    @XmlEnumValue("md5")
    MD_5("md5"),
    @XmlEnumValue("sha1")
    SHA_1("sha1"),
    @XmlEnumValue("sha256")
    SHA_256("sha256"),
    @XmlEnumValue("sha384")
    SHA_384("sha384"),
    @XmlEnumValue("sha512")
    SHA_512("sha512"),
    @XmlEnumValue("rmd160")
    RMD_160("rmd160"),
    @XmlEnumValue("tiger")
    TIGER("tiger"),
    @XmlEnumValue("crc32")
    CRC_32("crc32");
    private final String value;

    HashType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HashType fromValue(String v) {
        for (HashType c: HashType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

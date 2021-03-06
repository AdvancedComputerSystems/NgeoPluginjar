//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.26 at 01:34:15 PM CEST 
//


package org.metalinker;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for multimediaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="multimediaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="audio" type="{http://www.metalinker.org/}audioType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="video" type="{http://www.metalinker.org/}videoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multimediaType", propOrder = {
    "audio",
    "video"
})
public class MultimediaType {

    protected List<AudioType> audio;
    protected List<VideoType> video;

    /**
     * Gets the value of the audio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the audio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAudio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AudioType }
     * 
     * 
     */
    public List<AudioType> getAudio() {
        if (audio == null) {
            audio = new ArrayList<AudioType>();
        }
        return this.audio;
    }

    /**
     * Gets the value of the video property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the video property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVideo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VideoType }
     * 
     * 
     */
    public List<VideoType> getVideo() {
        if (video == null) {
            video = new ArrayList<VideoType>();
        }
        return this.video;
    }

}

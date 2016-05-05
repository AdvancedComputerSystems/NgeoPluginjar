//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.26 at 01:34:15 PM CEST 
//


package org.metalinker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for fileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="changelog" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}language" minOccurs="0"/>
 *         &lt;element name="license" type="{http://www.metalinker.org/}licenseType" minOccurs="0"/>
 *         &lt;element name="logo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mimetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="multimedia" type="{http://www.metalinker.org/}multimediaType" minOccurs="0"/>
 *         &lt;element name="os" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publisher" type="{http://www.metalinker.org/}publisherType" minOccurs="0"/>
 *         &lt;element name="relations" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="releasedate" type="{http://www.metalinker.org/}RFC822DateTime" minOccurs="0"/>
 *         &lt;element name="resources" type="{http://www.metalinker.org/}resourcesType"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="screenshot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upgrade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verification" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="hash" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" default="sha1" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="signature" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" default="PGP" />
 *                           &lt;attribute name="file" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="pieces" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="hash" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="piece">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                           &lt;minInclusive value="0"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileType", propOrder = {

})
public class FileType {

    protected String changelog;
    protected String copyright;
    protected String description;
    protected String identity;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String language;
    protected LicenseType license;
    protected String logo;
    protected String mimetype;
    protected MultimediaType multimedia;
    protected String os;
    protected PublisherType publisher;
    protected String relations;
    protected String releasedate;
    @XmlElement(required = true)
    protected ResourcesType resources;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger size;
    protected String screenshot;
    protected String tags;
    protected String upgrade;
    protected FileType.Verification verification;
    protected String version;
    @XmlAttribute
    protected String name;

    /**
     * Gets the value of the changelog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangelog() {
        return changelog;
    }

    /**
     * Sets the value of the changelog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangelog(String value) {
        this.changelog = value;
    }

    /**
     * Gets the value of the copyright property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the value of the copyright property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyright(String value) {
        this.copyright = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentity(String value) {
        this.identity = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the license property.
     * 
     * @return
     *     possible object is
     *     {@link LicenseType }
     *     
     */
    public LicenseType getLicense() {
        return license;
    }

    /**
     * Sets the value of the license property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseType }
     *     
     */
    public void setLicense(LicenseType value) {
        this.license = value;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogo(String value) {
        this.logo = value;
    }

    /**
     * Gets the value of the mimetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimetype() {
        return mimetype;
    }

    /**
     * Sets the value of the mimetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimetype(String value) {
        this.mimetype = value;
    }

    /**
     * Gets the value of the multimedia property.
     * 
     * @return
     *     possible object is
     *     {@link MultimediaType }
     *     
     */
    public MultimediaType getMultimedia() {
        return multimedia;
    }

    /**
     * Sets the value of the multimedia property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultimediaType }
     *     
     */
    public void setMultimedia(MultimediaType value) {
        this.multimedia = value;
    }

    /**
     * Gets the value of the os property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOs() {
        return os;
    }

    /**
     * Sets the value of the os property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOs(String value) {
        this.os = value;
    }

    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     possible object is
     *     {@link PublisherType }
     *     
     */
    public PublisherType getPublisher() {
        return publisher;
    }

    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublisherType }
     *     
     */
    public void setPublisher(PublisherType value) {
        this.publisher = value;
    }

    /**
     * Gets the value of the relations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelations() {
        return relations;
    }

    /**
     * Sets the value of the relations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelations(String value) {
        this.relations = value;
    }

    /**
     * Gets the value of the releasedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleasedate() {
        return releasedate;
    }

    /**
     * Sets the value of the releasedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleasedate(String value) {
        this.releasedate = value;
    }

    /**
     * Gets the value of the resources property.
     * 
     * @return
     *     possible object is
     *     {@link ResourcesType }
     *     
     */
    public ResourcesType getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourcesType }
     *     
     */
    public void setResources(ResourcesType value) {
        this.resources = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSize(BigInteger value) {
        this.size = value;
    }

    /**
     * Gets the value of the screenshot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Sets the value of the screenshot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScreenshot(String value) {
        this.screenshot = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTags(String value) {
        this.tags = value;
    }

    /**
     * Gets the value of the upgrade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpgrade() {
        return upgrade;
    }

    /**
     * Sets the value of the upgrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpgrade(String value) {
        this.upgrade = value;
    }

    /**
     * Gets the value of the verification property.
     * 
     * @return
     *     possible object is
     *     {@link FileType.Verification }
     *     
     */
    public FileType.Verification getVerification() {
        return verification;
    }

    /**
     * Sets the value of the verification property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileType.Verification }
     *     
     */
    public void setVerification(FileType.Verification value) {
        this.verification = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="hash" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" default="sha1" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="signature" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" default="PGP" />
     *                 &lt;attribute name="file" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="pieces" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="hash" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="piece">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                 &lt;minInclusive value="0"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *                 &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "hash",
        "signature",
        "pieces"
    })
    public static class Verification {

        protected List<FileType.Verification.Hash> hash;
        protected List<FileType.Verification.Signature> signature;
        protected FileType.Verification.Pieces pieces;

        /**
         * Gets the value of the hash property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hash property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHash().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FileType.Verification.Hash }
         * 
         * 
         */
        public List<FileType.Verification.Hash> getHash() {
            if (hash == null) {
                hash = new ArrayList<FileType.Verification.Hash>();
            }
            return this.hash;
        }

        /**
         * Gets the value of the signature property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the signature property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSignature().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FileType.Verification.Signature }
         * 
         * 
         */
        public List<FileType.Verification.Signature> getSignature() {
            if (signature == null) {
                signature = new ArrayList<FileType.Verification.Signature>();
            }
            return this.signature;
        }

        /**
         * Gets the value of the pieces property.
         * 
         * @return
         *     possible object is
         *     {@link FileType.Verification.Pieces }
         *     
         */
        public FileType.Verification.Pieces getPieces() {
            return pieces;
        }

        /**
         * Sets the value of the pieces property.
         * 
         * @param value
         *     allowed object is
         *     {@link FileType.Verification.Pieces }
         *     
         */
        public void setPieces(FileType.Verification.Pieces value) {
            this.pieces = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" default="sha1" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Hash {

            @XmlValue
            protected String value;
            @XmlAttribute
            protected HashType type;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link HashType }
             *     
             */
            public HashType getType() {
                if (type == null) {
                    return HashType.SHA_1;
                } else {
                    return type;
                }
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link HashType }
             *     
             */
            public void setType(HashType value) {
                this.type = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="hash" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="piece">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                       &lt;minInclusive value="0"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
         *       &lt;attribute name="type" type="{http://www.metalinker.org/}hashType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "hash"
        })
        public static class Pieces {

            @XmlElement(required = true)
            protected List<FileType.Verification.Pieces.Hash> hash;
            @XmlAttribute
            @XmlSchemaType(name = "nonNegativeInteger")
            protected BigInteger length;
            @XmlAttribute
            protected HashType type;

            /**
             * Gets the value of the hash property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the hash property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getHash().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link FileType.Verification.Pieces.Hash }
             * 
             * 
             */
            public List<FileType.Verification.Pieces.Hash> getHash() {
                if (hash == null) {
                    hash = new ArrayList<FileType.Verification.Pieces.Hash>();
                }
                return this.hash;
            }

            /**
             * Gets the value of the length property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getLength() {
                return length;
            }

            /**
             * Sets the value of the length property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setLength(BigInteger value) {
                this.length = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link HashType }
             *     
             */
            public HashType getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link HashType }
             *     
             */
            public void setType(HashType value) {
                this.type = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="piece">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *             &lt;minInclusive value="0"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Hash {

                @XmlValue
                protected String value;
                @XmlAttribute
                protected BigInteger piece;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the piece property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getPiece() {
                    return piece;
                }

                /**
                 * Sets the value of the piece property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setPiece(BigInteger value) {
                    this.piece = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" default="PGP" />
         *       &lt;attribute name="file" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Signature {

            @XmlValue
            protected String value;
            @XmlAttribute
            protected String type;
            @XmlAttribute
            protected String file;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                if (type == null) {
                    return "PGP";
                } else {
                    return type;
                }
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the file property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFile() {
                return file;
            }

            /**
             * Sets the value of the file property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFile(String value) {
                this.file = value;
            }

        }

    }

}

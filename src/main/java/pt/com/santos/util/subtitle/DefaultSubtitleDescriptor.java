package pt.com.santos.util.subtitle;

public class DefaultSubtitleDescriptor extends AbstractSubtitleDescriptor
    implements SubtitleDescriptor {
    protected String subFileName;
    protected String subHash;
    protected String ISO639;
    protected String subLink;
    protected String subDownloadLink;
    protected String data;
    protected String downFileName;
    protected String languageName;
    protected String type;
    protected String name;

    public DefaultSubtitleDescriptor(String subFileName, String ISO639) {
        this(subFileName, ISO639, null, null, null);
    }
    
    public DefaultSubtitleDescriptor(String subFileName, String ISO639, 
            String languageName, String type, String name) {
        this.subFileName = subFileName;
        this.ISO639 = ISO639;
        this.languageName = languageName;
        this.type = type;
        this.name = name;
    }

    public String getISO639() {
        return ISO639;
    }

    public void setISO639(String ISO639) {
        this.ISO639 = ISO639;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDownFileName() {
        return downFileName;
    }

    public void setDownFileName(String downFileName) {
        this.downFileName = downFileName;
    }

    public String getSubDownloadLink() {
        return subDownloadLink;
    }

    public void setSubDownloadLink(String subDownloadLink) {
        this.subDownloadLink = subDownloadLink;
    }

    public String getSubFileName() {
        return subFileName;
    }

    public void setSubFileName(String subFileName) {
        this.subFileName = subFileName;
    }

    public String getSubHash() {
        return subHash;
    }

    public void setSubHash(String subHash) {
        this.subHash = subHash;
    }

    public String getSubLink() {
        return subLink;
    }

    public void setSubLink(String subLink) {
        this.subLink = subLink;
    }

    public String getName() {
        return name;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getType() {
        return type;
    }

}
package Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Multimedia {
    private Integer number;

    private String name;

    private String path;

    private Integer type;

    private String download;

    private Integer click;

    private String description;

    private String uploaduserid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date uploaddate;

    private String filename;

    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUploaduserid() {
        return uploaduserid;
    }

    public void setUploaduserid(String uploaduserid) {
        this.uploaduserid = uploaduserid == null ? null : uploaduserid.trim();
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", download=" + download +
                ", click=" + click +
                ", description='" + description + '\'' +
                ", uploaduserid='" + uploaduserid + '\'' +
                ", uploaddate=" + uploaddate +
                ", filename='" + filename + '\'' +
                '}';
    }
}
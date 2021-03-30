package pojo;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class PubImageMessage extends Message{
    BufferedImage image;
}

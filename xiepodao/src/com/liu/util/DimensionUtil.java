package com.liu.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class DimensionUtil {
    public static Rectangle getBounds() {
        // 点击缩小之后的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 屏幕边界
        Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        final Rectangle frameBounds=new Rectangle(
                screenInsets.left, screenInsets.top,
                screenSize.width-screenInsets.left-screenInsets.right,
                screenSize.height-screenInsets.top-screenInsets.bottom);
        return frameBounds;
    }
}

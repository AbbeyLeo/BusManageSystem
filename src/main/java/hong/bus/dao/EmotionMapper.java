package hong.bus.dao;

import hong.bus.pojo.Emotion;
import hong.bus.pojo.EmotionKey;

public interface EmotionMapper {
    int deleteByPrimaryKey(EmotionKey key);

    int insert(Emotion record);

    int insertSelective(Emotion record);

    Emotion selectByPrimaryKey(EmotionKey key);

    int updateByPrimaryKeySelective(Emotion record);

    int updateByPrimaryKey(Emotion record);
}
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
#LOCAL_MODULE表示生成的库的名字，前面的lib和后缀名不用写
LOCAL_MODULE    := HelloWorld
LOCAL_SRC_FILES := dynamicImpl.c 
LOCAL_LDLIBS    := -llog -ldl

include $(BUILD_SHARED_LIBRARY)
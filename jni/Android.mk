LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
#LOCAL_MODULE��ʾ���ɵĿ�����֣�ǰ���lib�ͺ�׺������д
LOCAL_MODULE    := HelloWorld
LOCAL_SRC_FILES := dynamicImpl.c 
LOCAL_LDLIBS    := -llog -ldl

include $(BUILD_SHARED_LIBRARY)
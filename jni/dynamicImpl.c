#include <string.h>
#include <jni.h>
#include <android/log.h>
#define ALOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))
#define LOG_TAG "hello"

jstring Java_com_dynamic_impl_DynamicImpl_helloWorldFromJNI(JNIEnv* env,
		jobject thiz) {
//	return (*env)->NewStringUTF(env, "HelloWorld! I am from JNI !");
//	char * hello
//	return env->NewStringUTF("sdfghsdflh");
	return (*env)->NewStringUTF(env, "HelloWorld! I am from JNI loading !");
}

jint Java_com_dynamic_impl_DynamicImpl_showIntFromJNI(JNIEnv* env,
		jobject thiz) {
	int m = 10;
	return (jint) m;
}

// JNI_onLoad, must be extern C
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
	JNIEnv* env = NULL;
	jint result = -1;
	if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
		ALOGD("ERROR: GetEnv failed\n");
//		goto bail;
	}
	ALOGD("ERROR: GetEnv failed\n+%d", JNI_VERSION_1_4);
	return result = JNI_VERSION_1_4;
}

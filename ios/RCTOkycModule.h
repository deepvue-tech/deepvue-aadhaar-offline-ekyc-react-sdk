//  RCTCalendarModule.h
#import <React/RCTBridgeModule.h>
#import "OkycSdkHandler.h"
@import Flutter;
@interface RCTOkycModule : NSObject <RCTBridgeModule>
@property (nonatomic,strong) FlutterEngine *flutterEngine;
@end

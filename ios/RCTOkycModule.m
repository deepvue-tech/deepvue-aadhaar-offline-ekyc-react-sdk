#import "RCTOkycModule.h"
@import Foundation;
@import Flutter;
@import UIKit;
@import FlutterPluginRegistrant;
@implementation RCTOkycModule

RCT_EXPORT_MODULE(OkycHandler);

RCT_EXPORT_METHOD(initSdk:(NSString *)clientId clientSecret:(NSString *)clientSecret baseUrl:(NSString *)baseUrl imageUrl:(NSString *)imageUrl success:(RCTResponseSenderBlock)success failure:(RCTResponseSenderBlock)failure )
{
  dispatch_async(dispatch_get_main_queue(), ^{
    NSString * imagePath = nil;
    if([imageUrl length]>0){
      imagePath = imageUrl;
    }
    OkycSdkHandler * okycHandler = [[OkycSdkHandler alloc] init];
    [okycHandler init:baseUrl clientId:clientId clientSecret:clientSecret useFaceMatch:NO successBlock:^(NSString *response) {
      success([NSArray arrayWithObjects: response, nil]);
    } failureBlock:^(NSInteger code) {
      failure([NSArray arrayWithObjects: [NSNumber numberWithInteger:code], nil]);
    } imageUrl:imagePath];
    [okycHandler startSdk];
  });
}
@end

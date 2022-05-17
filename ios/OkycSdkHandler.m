#import "OkycSdkHandler.h"
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@implementation OkycSdkHandler

- (void) init: (NSString *) baseUrl clientId: (NSString *) clientId clientSecret: (NSString *) clientSecret useFaceMatch: (BOOL) useFaceMatch successBlock: (SuccessBlock) successBlock failureBlock: (FailureBlock) failureBlock imageUrl: (NSString *) imageUrl{
  self.channel = @"com.ayush.ekyc.flutter";
  self.baseUrl = baseUrl;
  self.clientId = clientId;
  self.clientSecret = clientSecret;
  self.useFaceMatch = useFaceMatch;
  self.successBlock = successBlock;
  self.failureBlock = failureBlock;
  self.imageUrl = imageUrl;
  self.flutterEngine = [[FlutterEngine alloc] initWithName:@"flutter_engine"];
  [self.flutterEngine run];
  [GeneratedPluginRegistrant registerWithRegistry:self.flutterEngine];
}

- (void) startSdk{
  id rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  
  FlutterViewController * flutterViewController =  [[FlutterViewController alloc] initWithEngine:self.flutterEngine nibName:nil bundle:nil];
  [self setupMethodChannel:flutterViewController];
  flutterViewController.modalPresentationStyle = UIModalPresentationFullScreen;
  [rootViewController presentViewController:flutterViewController animated:true completion:nil];
}

- (void) setupMethodChannel: (FlutterViewController *) flutterViewController{
  NSMutableDictionary * jsonObject = [[NSMutableDictionary alloc] init];
  [jsonObject setValue:self.baseUrl forKey:@"baseUrl"];
  [jsonObject setValue:self.clientId forKey:@"clientId"];
  [jsonObject setValue:self.clientSecret forKey:@"clientSecret"];
  [jsonObject setValue:[NSNumber numberWithBool:self.useFaceMatch] forKey:@"useFaceMatch"];
  [jsonObject setValue:self.imageUrl forKey:@"imageUrl"];
  
  NSString * convertedString = nil;
  @try {
    NSData * sdkData = [NSJSONSerialization dataWithJSONObject:jsonObject options:NSJSONWritingPrettyPrinted error:nil];
    convertedString = [[NSString alloc] initWithData:sdkData encoding:NSUTF8StringEncoding];
  } @catch(NSException * exception){
    NSLog(@"%@",exception.name);
  }
  
  FlutterMethodChannel * resultDataChannel = [FlutterMethodChannel methodChannelWithName:self.channel binaryMessenger:flutterViewController.binaryMessenger];
  [resultDataChannel invokeMethod:@"setUpSdk" arguments: convertedString];
  [resultDataChannel setMethodCallHandler:^(FlutterMethodCall * _Nonnull call, FlutterResult  _Nonnull result) {
      if([call.method isEqual:@"onFailure"]){
        self.failureBlock([call.arguments integerValue]);
      }else if([call.method isEqual:@"onSuccess"]){
        self.successBlock([call.arguments stringValue]);
      }
  }];
}

@end

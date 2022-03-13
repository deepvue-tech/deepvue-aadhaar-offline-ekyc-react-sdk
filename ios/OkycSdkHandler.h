//
//  OkycSdkHandler.h
//  OkycReact
//
//  Created by Ayush Jain on 06/03/22.
//

#ifndef OkycSdkHandler_h
#define OkycSdkHandler_h


#endif /* OkycSdkHandler_h */

@import Flutter;
@import UIKit;
@import FlutterPluginRegistrant;

typedef void (^SuccessBlock)(NSString *response);

typedef void (^FailureBlock)(NSInteger code);

@interface OkycSdkHandler : NSObject

@property (nonatomic, strong) NSString * channel;
@property (nonatomic, strong) NSString * baseUrl;
@property (nonatomic, strong) NSString * clientId;
@property (nonatomic, strong) NSString * clientSecret;
@property (nonatomic) BOOL useFaceMatch;
@property (nonatomic, strong) FlutterEngine * flutterEngine;
@property (nonatomic, strong) SuccessBlock successBlock;
@property (nonatomic, strong) FailureBlock failureBlock;
@property (nonatomic, strong) NSString * imageUrl;

- (void) init: (NSString *) baseUrl clientId: (NSString *) clientId clientSecret: (NSString *) clientSecret useFaceMatch: (BOOL) useFaceMatch successBlock: (SuccessBlock) successBlock failureBlock: (FailureBlock) failureBlock imageUrl: (NSString *) imageUrl;

- (void) startSdk;
@end



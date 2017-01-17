function x = HermiteMin(x1, x2, f1, f2, df1, df2) 
% 
%        x = HermiteMin(x1, x2, f1, f2, df1, df2); 
%
% Input: Information needed to construct a Hermite interpolating 
%        polynomial: 
%                       x1 < x2 %                       f1 = f(x1), f2 = f(x2) 
%                       df1 = f¡¯(x1), df2 = f¡¯(x2) 
%
% Output: x : scalar value at which the Hermite interpolating polynomial 
%             attains its minimum.
% 
z = (3.*(f1-f2))./(x2-x1)+df1+df2; 
w = (z.^2 - df1.*df2).^(1/2); 
x = x2 - ((df2+w-z)./(df2-df1+2.*w)).*(x2-x1);
end 
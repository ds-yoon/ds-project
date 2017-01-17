function x = CubicMin(x1, x2, fname, MaxIts) 
%
%        x = CubicMin(x1, x2, fname, MaxIts) 
% 
% Input: 
%           x1 < x2 : points that correspond to an initial bracket 
%                     containing the minimum; that is, we assume 
%           f¡¯(x1) < 0 and f¡¯(x2) > 0. 
%
% fname : character string containing the name of the file that
%         implements evaluation of both f(x) and f¡¯(x).
%         Note that the implementation of f(x) 
%         should be able to evaluate f at a vector of x values.
% 
% MaxIts : maximum number of allowable iterations. 
%
% Output: 
%           x : vector containing the approximations of the minimum
%               at each iteration.
% 
[f1, df1] = feval(fname,x1); 
[f2, df2] = feval(fname,x2);
x3 = HermiteMin(x1, x2, f1, f2, df1, df2);
[f3, df3] = feval(fname,x3);
k = 1; 
x = [x1; x2; x3]; 
while (abs(x3-x1) > sqrt(eps)*max(abs([x1, x2, x3])) && k < MaxIts)    
    k = k + 1;   
    if (df1 > 0 && df2 < 0)     
        if (df3 < 0)        
            x2 = x3;     
            f2 = f3;    
            df2 = df3;   
        else
            x1 = x3;     
            f1 = f3;    
            df1 = df3;   
        end
    else
        if (df3 > 0)     
            x2 = x3;   
            f2 = f3;      
            df2 = df3;    
        else
            x1 = x3;   
            f1 = f3;  
            df1 = df3; 
        end
    end
    x3 = HermiteMin(x1, x2, f1, f2, df1, df2); 
    [f3, df3] = feval(fname,x3);  
    x = [x; x3];
end














precision mediump float;

uniform float iTime;
uniform vec2 iResolution;

vec3 ray(vec2 pos) {
	float angle = mod(((iTime *1000.0) / 15.0 +1.0),360.0);
	if (angle >= 180.0) {
		angle = (360.0 - angle) * 2.0;
	} else {
		angle = angle * 2.0;
	}
	float amount = min(1.0, max(0.0, angle / 360.0));
	vec3 c1 = vec3(186.0 / 255.0, 83.0 / 255.0, 200.0 / 255.0);
	vec3 c2 = vec3(236.0 / 255.0, 133.0 / 255.0, 209.0 / 255.0);
	c2 += vec3(28.0 / 255.0, 167.0 / 255.0, 222.0 / 255.0);
	vec3 c3 = mix(c1,c1,amount);
	vec3 c4 = mix(c2,c2,amount);
	vec3 c5 = mix(c3,c4,amount);
	vec3 c6 = mix(c4,c3,amount);
	vec3 cB = mix(c5,c6,amount * 2.0);
	cB += mix(c6,c5,1.0) / 1.0;
	return vec3(pos,cB);
}

void main( void ) {

	vec2 position = ( gl_FragCoord.xy / iResolution.xy );
	

	gl_FragColor = vec4(ray(position),1);

}